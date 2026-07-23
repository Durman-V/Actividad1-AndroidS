import { Link } from 'react-router-dom';

const PerroCard = ({ perro }) => {
  const isAdoptado = perro.estado === 'ADOPTADO';

  return (
    <div className="card h-100 shadow-sm">
      <img 
        src={perro.fotoUrl || 'https://via.placeholder.com/300x250?text=Sin+Foto'} 
        className="card-img-top" 
        alt={perro.nombre} 
      />
      <div className="card-body d-flex flex-column">
        <h5 className="card-title">
          {perro.nombre} 
          <span className={`badge ms-2 ${isAdoptado ? 'badge-adoptado' : 'badge-disponible'}`}>
            {perro.estado}
          </span>
        </h5>
        <p className="card-text mb-1"><strong>Raza:</strong> {perro.raza}</p>
        <p className="card-text mb-1"><strong>Edad:</strong> {perro.edad} años</p>
        <p className="card-text mb-1"><strong>Tamaño:</strong> {perro.tamano}</p>
        <p className="card-text mb-3"><strong>Sexo:</strong> {perro.sexo}</p>
        <div className="mt-auto">
          <Link to={`/perro/${perro.id}`} className="btn btn-primary w-100">
            Ver Detalles
          </Link>
        </div>
      </div>
    </div>
  );
};

export default PerroCard;