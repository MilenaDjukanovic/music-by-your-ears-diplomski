package com.miggie.musicbyyourears.service.impl;

import com.miggie.musicbyyourears.repo.entity.SoundDto;
import com.miggie.musicbyyourears.service.SoundMixingService;
import ie.corballis.sox.Sox;
import ie.corballis.sox.WrongParametersException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultSoundMixingService implements SoundMixingService{

    @Value("${sox.path.home}") @Setter
    private String SOX_HOME;

    @Value("${sox.path.temp}") @Setter
    private String SOX_TEMP;

    @Value("${sox.default.extension}")
    private String SOX_DEFAULT_EXTENSION;

    @Override
    public SoundDto mixSounds(List<SoundDto> sounds){
        if(sounds.size() == 1){
            return sounds.get(0);
        }

        Sox sox = new Sox(this.SOX_HOME);

        List<String> soundFilePaths = this.createTempSoundFiles(sounds);
        List<String> soundFilesForMixing = this.prepareSoundsForMixing(soundFilePaths);

        this.addInputFilesForSox(sox, soundFilesForMixing);

        String outputFilePath = this.SOX_TEMP + '/' + UUID.randomUUID() + "." + this.SOX_DEFAULT_EXTENSION;
        this.createMixedFile(sox, outputFilePath);

        byte[] mixedSound = this.getByteArrayFromFile(outputFilePath);

        soundFilePaths.add(outputFilePath);
        this.deleteTempFiles(soundFilePaths);
        this.deleteTempFiles(soundFilesForMixing);

        SoundDto soundDto = new SoundDto();
        soundDto.setAudio(mixedSound);
        soundDto.setName("mixed." + this.SOX_DEFAULT_EXTENSION);

        return soundDto;
    }

    private List<String> createTempSoundFiles(List<SoundDto> sounds){
        List<String> soundsToMerge = new ArrayList<>();
        sounds.forEach(sound -> {
            try{
                String[] soundNameParts = sound.getName().split("\\.");
                String soundExtension = soundNameParts[soundNameParts.length - 1];

                String filePath = this.SOX_TEMP + "/" + UUID.randomUUID() + "." + soundExtension;
                FileUtils.writeByteArrayToFile(new File(filePath), sound.getAudio());

                soundsToMerge.add(filePath);
            }catch(IOException e){
                e.printStackTrace();
            }
        });

        return soundsToMerge;
    }

    public List<String> prepareSoundsForMixing(List<String> soundFilePaths){
        // Convert all files to .wave
        List<String> convertedFilePaths = new ArrayList<>();
        soundFilePaths.forEach(soundFilePath -> {
            try{
                Sox conversionSox = new Sox(this.SOX_HOME);
                conversionSox.inputFile(soundFilePath);

                String outputFileName = this.SOX_TEMP + '/' + UUID.randomUUID() + "." + this.SOX_DEFAULT_EXTENSION;
                conversionSox.outputFile(outputFileName);
                conversionSox.execute();

                convertedFilePaths.add(outputFileName);
            }catch(WrongParametersException | IOException e){
                e.printStackTrace();
            }
        });

        this.deleteTempFiles(soundFilePaths);
        soundFilePaths = convertedFilePaths;

        // Get the longest file
        int longestFileIndex = -1;
        float longestFileDuration = -1;
        for(int i = 0; i < soundFilePaths.size(); ++i){
            String soundFilePath = soundFilePaths.get(i);

            float durationInSeconds = this.getFileDuration(soundFilePath);
            if(longestFileDuration < durationInSeconds){
                longestFileIndex = i;
                longestFileDuration = durationInSeconds;
            }
        }

        List<String> filePathsForMixing = new ArrayList<>();
        filePathsForMixing.add(soundFilePaths.get(longestFileIndex));
        // Match as close as possible the length of the files
        for(int i = 0; i < soundFilePaths.size(); ++i){
            if(i == longestFileIndex){
                continue;
            }
            String soundFilePath = soundFilePaths.get(i);

            float fileDuration = this.getFileDuration(soundFilePath);

            String[] soundNameParts = soundFilePath.split("\\.");
            String soundExtension = soundNameParts[soundNameParts.length - 1];

            String extendedFilePath = this.SOX_TEMP + "/" + UUID.randomUUID() + "." + soundExtension;

            filePathsForMixing.add(extendedFilePath);
            int numberOfTimesToExtend = (fileDuration == 0) ? 0 : (int) (longestFileDuration / fileDuration);
            Sox sox = new Sox(this.SOX_HOME);
            try{
                sox.inputFile(soundFilePath);

                for(int j = 0; j < numberOfTimesToExtend - 1; ++j){
                    sox.inputFile(soundFilePath);
                }

                sox.outputFile(extendedFilePath);
                sox.execute();
            }catch(WrongParametersException | IOException e){
                e.printStackTrace();
            }
        }
        String longestFileName = soundFilePaths.get(longestFileIndex);

        List<String> tempFilesToDelete = soundFilePaths
                .stream()
                .filter(soundFilePath ->
                        !StringUtils.equalsIgnoreCase(soundFilePath, longestFileName)).collect(Collectors.toList());

        this.deleteTempFiles(tempFilesToDelete);

        return filePathsForMixing;
    }

    private float getFileDuration(String soundFilePath){
        File soundFile = new File(soundFilePath);
        try{
            AudioFormat audioFormat = AudioSystem.getAudioInputStream(soundFile).getFormat();
            long audioFileLength = soundFile.length();
            int frameSize = audioFormat.getFrameSize();
            float frameRate = audioFormat.getFrameRate();

            return (audioFileLength / (frameSize * frameRate));
        }catch(UnsupportedAudioFileException | IOException ignored){

        }

        return 0;
    }

    private void createMixedFile(Sox sox, String outputFilePath){
        try{
            sox.outputFile(outputFilePath);
            sox.argument("-m");
            sox.execute();
        }catch(WrongParametersException | IOException e){
            e.printStackTrace();
        }
    }

    private void addInputFilesForSox(Sox sox, List<String> inputFilePaths){
        inputFilePaths.forEach(inputFilePath -> {
            try{
                sox.inputFile(inputFilePath);
            }catch(WrongParametersException e){
                e.printStackTrace();
            }
        });
    }

    private byte[] getByteArrayFromFile(String filePath){
        try{
            return FileUtils.readFileToByteArray(new File(filePath));
        }catch(IOException e){
            e.printStackTrace();
        }

        return new byte[0];
    }

    private void deleteTempFiles(List<String> filePaths){
        filePaths.forEach(filePath -> {
            try{
                FileUtils.forceDelete(new File(filePath));
            }catch(IOException ignored){

            }
        });
    }
}
