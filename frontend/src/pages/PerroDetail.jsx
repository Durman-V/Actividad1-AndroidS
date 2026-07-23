import { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { perroService } from '../services/api';

const PerroDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [perro, setPerro] = useState(null);

  useEffect(() => {
    perroService.getById(id)
      .then(res => setPerro(res.data))
      .catch(err => console.error(err));
  }, [id]);

  const handleAdoptar = async () => {
    if(window.confirm(`¿Estás seguro de que quieres adoptar a ${perro.nombre}?`)) {
      try {
        await perroService.adopt(id);
        alert(`¡Felicidades! Has adoptado a ${perro.nombre}.`);
        setPerro({ ...perro, estado: 'ADOPTADO' });
      } catch (error) {
        alert("Hubo un error al procesar la adopción.");
      }
    }
  };

  const handleEliminar = async () => {
    if(window.confirm("¿Seguro que deseas eliminar este registro?")) {
      try {
        await perroService.delete(id);
        navigate('/');
      } catch (error) {
        alert("Error al eliminar");
      }
    }
  };

  if (!perro) return <div>Cargando...</div>;

  return (
    <div className="card shadow-lg mx-auto" style={{ maxWidth: '800px' }}>
      <div className="row g-0">
        <div className="col-md-5">
          <img src={perro.fotoUrl || 'https://via.placeholder.com/400x500?text=Sin+Foto'} className="img-fluid rounded-start h-100" style={{objectFit: 'cover'}} alt={perro.nombre} />
        </div>
        <div className="col-md-7">
          <div className="card-body">
            <h2 className="card-title">
              {perro.nombre} 
              <span className={`badge ms-3 fs-6 ${perro.estado === 'ADOPTADO' ? 'bg-danger' : 'bg-success'}`}>
                {perro.estado}
              </span>
            </h2>
            <hr />
            <p><strong>Raza:</strong> {perro.raza}</p>
            <p><strong>Edad:</strong> {perro.edad} años</p>
            <p><strong>Tamaño:</strong> {perro.tamano}</p>
            <p><strong>Sexo:</strong> {perro.sexo}</p>
            <p><strong>Descripción:</strong> {perro.descripcion}</p>
            
            <div className="d-grid gap-2 mt-4">
              {perro.estado === 'DISPONIBLE' && (
                <button onClick={handleAdoptar} className="btn btn-success btn-lg">🐾 Adoptar</button>
              )}
              {perro.estado === 'ADOPTADO' && (
                <button className="btn btn-secondary btn-lg" disabled>Ya ha sido adoptado</button>
              )}
              <div className="d-flex gap-2 mt-2">
                <Link to={`/editar/${perro.id}`} className="btn btn-warning flex-fill">Editar</Link>
                <button onClick={handleEliminar} className="btn btn-danger flex-fill">Eliminar</button>
                <Link to="/" className="btn btn-outline-dark flex-fill">Volver</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PerroDetail;