import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import NotificationProvider from './components/Notifications/NotificationProvider';


const root = document.getElementById('root');

createRoot((root)).render(
  <NotificationProvider>
    <App />
  </NotificationProvider>
)
