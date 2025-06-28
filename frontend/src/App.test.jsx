import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import App from './App';
import { vi } from 'vitest';

beforeEach(() => {
  // 1. Mocks für alle fetch-Aufrufe
  global.fetch = vi.fn().mockResolvedValue({
    json: () => Promise.resolve([]),
  });
});

test('renders input field and submit button', () => {
  render(<App />);
  
  // Erwartet ein Eingabefeld (Textbox)
  expect(screen.getByRole('textbox')).toBeInTheDocument();
  
  // Erwartet einen Button mit Text "Absenden"
  expect(screen.getByRole('button', { name: /absenden/i })).toBeInTheDocument();
});

test('user can type into the input field', () => {
  render(<App />);
  
  const input = screen.getByRole('textbox');
  fireEvent.change(input, { target: { value: 'Meine Aufgabe' } });
  
  expect(input.value).toBe('Meine Aufgabe');
});

test('watch button toggles star icon', async () => {
  fetch.mockResolvedValueOnce({ json: () => Promise.resolve([{ taskdescription: "Test", watched: false }]) });

  render(<App />);
  const starButton = await screen.findByText('☆');
  fireEvent.click(starButton);
  expect(await screen.findByText('⭐')).toBeInTheDocument();
});

