import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {
  animate,
  group,
  query,
  style,
  transition,
  trigger
} from '@angular/animations';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css',
  animations: [
    trigger('routeAnimations', [
      transition('* <=> *', [
        style({ position: 'relative' }),
        query(':enter, :leave', [
          style({
            position: 'absolute',
            top: 0,
            left: 0,
            width: '100%'
          })
        ], { optional: true }),
        group([
          query(':leave', [
            style({ opacity: 1, transform: 'translateY(0)' }),
            animate('200ms ease', style({ opacity: 0, transform: 'translateY(-8px)' }))
          ], { optional: true }),
          query(':enter', [
            style({ opacity: 0, transform: 'translateY(8px)' }),
            animate('200ms ease', style({ opacity: 1, transform: 'translateY(0)' }))
          ], { optional: true })
        ])
      ])
    ])
  ]
})
export class App {
  protected readonly title = signal('src');

  protected prepareRoute(outlet: RouterOutlet) {
    return outlet?.activatedRouteData?.['animation'] ?? '';
  }
}
