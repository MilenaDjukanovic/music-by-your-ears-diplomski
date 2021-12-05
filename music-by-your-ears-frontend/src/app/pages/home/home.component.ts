import { Component, OnInit } from '@angular/core';
import {flipCards} from '../../configuration/flip-cards';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public flipCardConfiguration = flipCards;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public navigateToCreateSound(): void {
    this.router.navigate(['/create-sound']);
  }

  public navigateToPlaylist(): void {
    this.router.navigate(['/playlists']);
  }
}
