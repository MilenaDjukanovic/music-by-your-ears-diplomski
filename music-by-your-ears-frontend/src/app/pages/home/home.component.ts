import { Component, OnInit } from '@angular/core';
import {comments} from '../../configuration/comments';
import {flipCards} from '../../configuration/flip-cards';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public comments = comments;

  public flipCardConfiguration = flipCards;

  constructor() { }

  ngOnInit(): void {
  }

}
