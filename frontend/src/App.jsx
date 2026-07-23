import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import PerroForm from './pages/PerroForm';
import PerroDetail from './pages/PerroDetail';

function App() {
  return (
    <Router>
      <Navbar />
      <div className="container mt-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/nuevo" element={<PerroForm />} />
          <Route path="/editar/:id" element={<PerroForm />} />
          <Route path="/perro/:id" element={<PerroDetail />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;