import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Navbar } from '../../shared/navbar/navbar';
import { Footer } from '../../shared/footer/footer';

@Component({
  selector: 'app-home',
  imports: [RouterLink, Navbar, Footer],
  templateUrl: './home.html'
})
export class Home {

}
