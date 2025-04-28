import React from 'react';
import Welcome from './components/Welcome';
import './components/Welcome.css';

function App() {
  return (
    <div className="App" style={{ 
      background: 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
      minHeight: '100vh',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      margin: 0,
      padding: 0
    }}>
      <Welcome />
    </div>
  );
}

export default App;