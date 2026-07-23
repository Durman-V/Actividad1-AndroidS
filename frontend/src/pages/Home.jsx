import { useEffect, useState } from 'react';
import { perroService } from '../services/api';
import PerroCard from '../components/PerroCard';

const Home = () => {
  const [perros, setPerros] = useState([]);
  const [busqueda, setBusqueda] = useState('');
  const [filtro, setFiltro] = useState('nombre');

  const cargarPerros = async () => {
    try {
      const { data } = await perroService.getAll();
      setPerros(data);
    } catch (error) {
      console.error("Error cargando perros:", error);
    }
  };

  useEffect(() => {
    cargarPerros();
  }, []);

  const handleBuscar = async (e) => {
    e.preventDefault();
    if (!busqueda) return cargarPerros();

    try {
      let res;
      if (filtro === 'nombre') res = await perroService.searchByName(busqueda);
      if (filtro === 'raza') res = await perroService.searchByBreed(busqueda);
      if (filtro === 'tamano') res = await perroService.searchBySize(busqueda.toUpperCase());
      setPerros(res.data);
    } catch (error) {
      console.error("Error en búsqueda:", error);
    }
  };

  return (
    <div>
      <h2 className="mb-4">Perros en adopción</h2>
      
      <form onSubmit={handleBuscar} className="row g-3 mb-4">
        <div className="col-md-3">
          <select className="form-select" value={filtro} onChange={(e) => setFiltro(e.target.value)}>
            <option value="nombre">Nombre</option>
            <option value="raza">Raza</option>
            <option value="tamano">Tamaño (PEQUENO, MEDIANO, GRANDE)</option>
          </select>
        </div>
        <div className="col-md-7">
          <input 
            type="text" 
            className="form-control" 
            placeholder="Buscar..." 
            value={busqueda} 
            onChange={(e) => setBusqueda(e.target.value)} 
          />
        </div>
        <div className="col-md-2">
          <button type="submit" className="btn btn-dark w-100">Buscar</button>
        </div>
      </form>

      <div className="row g-4">
        {perros.length === 0 ? (
          <div className="col-12"><p className="text-center text-muted">No se encontraron perros.</p></div>
        ) : (
          perros.map(perro => (
            <div className="col-md-4 col-lg-3" key={perro.id}>
              <PerroCard perro={perro} />
            </div>
          ))
        )}
      </div>
    </div>
  );
};

export default Home;