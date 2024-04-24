import './App.css'
import { useEffect, useState } from 'react';

function App() {
  /* eslint-disable @typescript-eslint/no-explicit-any */
  const [items, setItems] = useState<any[]>([]);
  useEffect(() => {
    fetch('http://localhost:8080/v1/items')
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setItems(data);
      });
  }, []);
  return (
    <>
      <h1>To Do</h1>
      <div>
      {items.map((item) => (
        <li key={item.id}>{item.description}</li>
      ))}
    </div>
    </>
  )
}

export default App
