import axios from 'axios';

// Instancia de Axios con la URL base del Backend
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const perroService = {
  getAll: () => api.get('/perros'),
  getById: (id) => api.get(`/perros/${id}`),
  create: (data) => api.post('/perros', data),
  update: (id, data) => api.put(`/perros/${id}`, data),
  delete: (id) => api.delete(`/perros/${id}`),
  adopt: (id) => api.patch(`/perros/${id}/adoptar`),
  searchByName: (name) => api.get(`/perros/buscar/nombre?valor=${name}`),
  searchByBreed: (breed) => api.get(`/perros/buscar/raza?valor=${breed}`),
  searchBySize: (size) => api.get(`/perros/buscar/tamano?valor=${size}`)
};

export default api;