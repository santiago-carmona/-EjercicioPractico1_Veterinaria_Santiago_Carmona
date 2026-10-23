/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
// Carga los datos del tipo en el modal Agregar o Editar
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('tipoServicioModal');
    if (!modal) {
        return;
    }

    const form = document.getElementById('tipoServicioForm');
    const header = document.getElementById('tipoServicioModalHeader');
    const titulo = document.getElementById('tipoServicioModalTitulo');
    const id = document.getElementById('tipoServicioId');
    const nombre = document.getElementById('tipoServicioNombre');
    const descripcion = document.getElementById('tipoServicioDescripcion');

    modal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) {
            return;
        }
        const modo = button.getAttribute('data-modo');

        // Limpia información de una apertura anterior
        form.reset();
        id.value = '';
        titulo.textContent = button.getAttribute('data-titulo') || '';
        header.classList.remove('bg-primary-subtle', 'bg-warning-subtle');

        if (modo === 'editar') {
            id.value = button.getAttribute('data-id') || '';
            nombre.value = button.getAttribute('data-nombre') || '';
            descripcion.value = button.getAttribute('data-descripcion') || '';
            header.classList.add('bg-warning-subtle');
        } else {
            header.classList.add('bg-primary-subtle');
        }
    });
});

