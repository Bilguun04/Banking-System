import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Navbar } from '../../shared/navbar/navbar';
import { Footer } from '../../shared/footer/footer';

interface Feature {
  icon: string;
  title: string;
  description: string;
}

@Component({
  selector: 'app-features',
  standalone: true,
  imports: [CommonModule, Navbar],
  templateUrl: './features.html',
  styleUrl: './features.css'
})
export class Features {
  features: Feature[] = [
    {
      icon: '🏦',
      title: 'Secure Banking',
      description: 'Bank with confidence using our state-of-the-art security measures and encryption protocols.'
    },
    {
      icon: '💸',
      title: 'Easy Transfers',
      description: 'Send and receive money instantly with our seamless transfer system.'
    },
    {
      icon: '📊',
      title: 'Financial Analytics',
      description: 'Track your spending patterns and get insights into your financial health.'
    },
    {
      icon: '💳',
      title: 'Virtual Cards',
      description: 'Create virtual cards for secure online shopping and subscriptions.'
    },
    {
      icon: '🔔',
      title: 'Real-time Notifications',
      description: 'Stay updated with instant alerts for all your account activities.'
    },
    {
      icon: '📱',
      title: 'Mobile Banking',
      description: 'Access your accounts anytime, anywhere with our mobile-friendly platform.'
    }
  ];
}