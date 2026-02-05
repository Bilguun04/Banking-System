import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Features } from './features';

describe('Features', () => {
  let component: Features;
  let fixture: ComponentFixture<Features>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Features]
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