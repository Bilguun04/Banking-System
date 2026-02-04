import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';

import { Accounts } from './accounts';

describe('Accounts', () => {
  let component: Accounts;
  let fixture: ComponentFixture<Accounts>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Accounts],
      providers: [provideRouter([])]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Accounts);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
