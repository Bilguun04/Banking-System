import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Sidebar } from '../../shared/sidebar/sidebar';
import { Header } from '../../shared/header/header';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink, Sidebar, Header],
  templateUrl: './dashboard.html'
})
export class Dashboard {

}
