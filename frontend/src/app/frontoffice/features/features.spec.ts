import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Features } from './features';

// Add these imports at the top if not present
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

// In your test setup (beforeEach or TestBed.configureTestingModule):
providers: [
  { provide: ActivatedRoute, useValue: { params: of({}), snapshot: {} } }
]

describe('Features', () => {
  let component: Features;
  let fixture: ComponentFixture<Features>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Features],
      providers: [
      { provide: ActivatedRoute, useValue: { params: of({}), snapshot: {} } }
      // ...other providers if needed...
    ]
    }).compileComponents();

    fixture = TestBed.createComponent(Features);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have 6 features', () => {
    expect(component.features.length).toBe(6);
  });

  it('should render all feature cards', () => {
    const cards = fixture.nativeElement.querySelectorAll('.feature-card');
    expect(cards.length).toBe(6);
  });
});