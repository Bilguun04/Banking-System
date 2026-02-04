import { Component } from '@angular/core';
import { Sidebar } from '../../shared/sidebar/sidebar';
import { Header } from '../../shared/header/header';

@Component({
  selector: 'app-transactions',
  imports: [Sidebar, Header],
  templateUrl: './transactions.html'
})
export class Transactions {

}
