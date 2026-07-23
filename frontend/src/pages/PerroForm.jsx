import { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { perroService } from '../services/api';

const PerroForm = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  
  const [formData, setFormData] = useState({
    nombre: '', raza: '', edad: '', tamano: 'MEDIANO', sexo: 'MACHO', 
    descripcion: '', fotoUrl: '', estado: 'DISPONIBLE'
  });
  const [errores, setErrores] = useState({});

  useEffect(() => {
    if (id) {
      perroService.getById(id).then(res => setFormData(res.data)).catch(console.error);
    }
  }, [id]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const validar = () => {
    let err = {};
    if (!formData.nombre.trim()) err.nombre = "El nombre es obligatorio";
    if (!formData.raza.trim()) err.raza = "La raza es obligatoria";
    if (formData.edad === '' || formData.edad < 0) err.edad = "Edad inválida (debe ser 0 o mayor)";
    if (!formData.descripcion.trim()) err.descripcion = "La descripción es obligatoria";
    setErrores(err);
    return Object.keys(err).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validar()) return;

    try {
      if (id) {
        await perroService.update(id, formData);
      } else {
        await perroService.create(formData);
      }
      navigate('/');
    } catch (error) {
      console.error("Error guardando perro:", error);
      alert("Error en el servidor al guardar los datos.");
    }
  };

  return (
    <div className="row justify-content-center">
      <div className="col-md-8">
        <div className="card shadow">
          <div className="card-header bg-dark text-white">
            <h4>{id ? 'Editar Perro' : 'Registrar Nuevo Perro'}</h4>
          </div>
          <div className="card-body">
            <form onSubmit={handleSubmit}>
              <div className="row mb-3">
                <div className="col-md-6">
                  <label className="form-label">Nombre</label>
                  <input type="text" className={`form-control ${errores.nombre ? 'is-invalid' : ''}`} name="nombre" value={formData.nombre} onChange={handleChange} />
                  {errores.nombre && <div className="invalid-feedback">{errores.nombre}</div>}
                </div>
                <div className="col-md-6">
                  <label className="form-label">Raza</label>
                  <input type="text" className={`form-control ${errores.raza ? 'is-invalid' : ''}`} name="raza" value={formData.raza} onChange={handleChange} />
                  {errores.raza && <div className="invalid-feedback">{errores.raza}</div>}
                </div>
              </div>
              <div className="row mb-3">
                <div className="col-md-4">
                  <label className="form-label">Edad (años)</label>
                  <input type="number" className={`form-control ${errores.edad ? 'is-invalid' : ''}`} name="edad" value={formData.edad} onChange={handleChange} />
                  {errores.edad && <div className="invalid-feedback">{errores.edad}</div>}
                </div>
                <div className="col-md-4">
                  <label className="form-label">Tamaño</label>
                  <select className="form-select" name="tamano" value={formData.tamano} onChange={handleChange}>
                    <option value="PEQUENO">Pequeño</option>
                    <option value="MEDIANO">Mediano</option>
                    <option value="GRANDE">Grande</option>
                  </select>
                </div>
                <div className="col-md-4">
                  <label className="form-label">Sexo</label>
                  <select className="form-select" name="sexo" value={formData.sexo} onChange={handleChange}>
                    <option value="MACHO">Macho</option>
                    <option value="HEMBRA">Hembra</option>
                  </select>
                </div>
              </div>
              <div className="mb-3">
                <label className="form-label">URL Foto</label>
                <input type="text" className="form-control" name="fotoUrl" value={formData.fotoUrl} onChange={handleChange} placeholder="https://..." />
              </div>
              <div className="mb-3">
                <label className="form-label">Descripción</label>
                <textarea className={`form-control ${errores.descripcion ? 'is-invalid' : ''}`} name="descripcion" rows="3" value={formData.descripcion} onChange={handleChange}></textarea>
                {errores.descripcion && <div className="invalid-feedback">{errores.descripcion}</div>}
              </div>
              <button type="submit" className="btn btn-success w-100">{id ? 'Actualizar' : 'Guardar'}</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default PerroForm;