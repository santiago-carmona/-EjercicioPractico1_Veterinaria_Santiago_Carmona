/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */

// Carga los datos de la servicio en el modal Agregar o Editar
document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('servicioModal');
    if (!modal) {
        return;
    }

    const form = document.getElementById('servicioForm');
    const header = document.getElementById('servicioModalHeader');
    const titulo = document.getElementById('servicioModalTitulo');
    const id = document.getElementById('servicioId');
    const nombre = document.getElementById('servicioNombre');
    const precio = document.getElementById('servicioPrecio');
    const duracion = document.getElementById('servicioDuracion');
    const tipoId = document.getElementById('servicioTipoId');

    modal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget;
        if (!button) {
            return;
        }
        const modo = button.getAttribute('data-modo');
        
       //Limpia información de una apertura anterior
        form.reset();
        id.value = '';
        tipoId.value = '';
        titulo.textContent = button.getAttribute('data-titulo') || '';
        header.classList.remove('bg-primary-subtle', 'bg-warning-subtle');

        if (modo === 'editar') {
            id.value = button.getAttribute('data-id') || '';
            nombre.value = button.getAttribute('data-nombre') || '';
            precio.value = button.getAttribute('data-precio') || '';
            duracion.value = button.getAttribute('data-duracion') || '';
            tipoId.value = button.getAttribute('data-tipoid') || '';
            header.classList.add('bg-warning-subtle');
        } else {
            header.classList.add('bg-primary-subtle');
        }
    });
});


