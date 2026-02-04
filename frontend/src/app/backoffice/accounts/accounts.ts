import { Component } from '@angular/core';
import { Sidebar } from '../../shared/sidebar/sidebar';
import { Header } from '../../shared/header/header';

@Component({
  selector: 'app-accounts',
  imports: [Sidebar, Header],
  templateUrl: './accounts.html'
})
export class Accounts {

}
