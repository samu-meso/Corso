import 'bootstrap/dist/css/bootstrap.min.css';
import { useEffect, useContext, useState } from 'react';
import Header from './components/Header';
import Products from './components/Products';
import Notifications from './components/Notifications/Notifications';
import NotificationContext from './components/Notifications/NotificationContext';
import axios from "axios";

function App() {
  const { addNotification, removeNotification } = useContext(NotificationContext);
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [err, setErr] = useState('');

  const loadProducts = async (controller = new AbortController()) => {
    setLoading(true);
    setProducts([]);
    setErr('');

    const loadingId = addNotification(
      'Caricamento',
      'Caricamento prodotti in corso...',
      {
        type: 'loading',
        persistent: true
      }
    );

    try {
      const response = await axios.get(
        "https://dummyjson.com/products",
        { signal: controller.signal }
      );

      setProducts(response.data.products);

      removeNotification(loadingId);

      addNotification(
        'Successo',
        'Prodotti caricati correttamente',
        {
          type: 'success',
          persistent: true
        }
      );
    } catch (error) {
      console.error('Errore fetch prodotti:', error);

      setErr(error.message);

      removeNotification(loadingId);

      addNotification(
        'Errore',
        'Errore caricamento prodotti',
        {
          type: 'error',
          persistent: true
        }
      );
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    const controller = new AbortController();

    loadProducts(controller);

    return () => {
      controller.abort();
      console.log("Component unmounted");
    };
  }, []);

  return (
    <>
      <Header onRefresh={loadProducts} />
      <Products products={products} pLoading={loading} error={err} />
      <Notifications />
    </>
  );
}

export default App;