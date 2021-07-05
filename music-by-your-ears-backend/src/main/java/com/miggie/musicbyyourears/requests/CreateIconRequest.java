package com.miggie.musicbyyourears.requests;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateIconRequest extends BaseIconRequest{

    /** File extension **/
    private String extension;
}
