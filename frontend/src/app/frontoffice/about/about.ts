import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Navbar } from '../../shared/navbar/navbar';
import { Footer } from '../../shared/footer/footer';

@Component({
  selector: 'app-about',
  imports: [RouterLink, Navbar, Footer],
  templateUrl: './about.html'
})
export class About {

}
