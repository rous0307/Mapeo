const API_BASE = "http://localhost:8080/api";

// ------------------- PROYECTOS -------------------
async function cargarProyectos() {
    const res = await fetch(`${API_BASE}/proyectos`);
    const data = await res.json();
    const container = document.getElementById("proyectos");
    container.innerHTML = "";

    data._embedded?.proyectos?.forEach(proyecto => {
        const card = document.createElement("div");
        card.className = "card mb-4 shadow-sm";
        card.innerHTML = `
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center mb-2">
                    <h3 class="card-title">${proyecto.nombre}</h3>
                    <div>
                        <button class="btn btn-warning btn-sm me-2" onclick="editarProyecto(${proyecto.idProyecto}, '${proyecto.nombre}', '${proyecto.fechaInicio}', '${proyecto.fechaFinalEstimada}')">Editar</button>
                        <button class="btn btn-danger btn-sm" onclick="eliminarProyecto(${proyecto.idProyecto})">Eliminar</button>
                    </div>
                </div>
                <p>Inicio: ${proyecto.fechaInicio} | Fin: ${proyecto.fechaFinalEstimada}</p>

                <h5>Etapas</h5>
                <form class="row g-2 mb-2" onsubmit="agregarEtapa(event, ${proyecto.idProyecto})">
                    <div class="col-md-3"><input type="text" class="form-control" placeholder="Fase" required></div>
                    <div class="col-md-3"><input type="date" class="form-control" required></div>
                    <div class="col-md-3"><input type="date" class="form-control" required></div>
                    <div class="col-md-2"><input type="number" class="form-control" placeholder="Monto Total" required></div>
                    <div class="col-md-1"><button class="btn btn-success w-100">Agregar</button></div>
                </form>

                <table class="table table-striped" id="tabla-etapas-${proyecto.idProyecto}">
                    <thead>
                        <tr>
                            <th>Fase</th>
                            <th>Inicio</th>
                            <th>Fin</th>
                            <th>Presupuesto</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        `;
        container.appendChild(card);
        cargarEtapas(proyecto.idProyecto);
    });
}

// Crear o editar proyecto
document.getElementById("form-proyecto").addEventListener("submit", async e => {
    e.preventDefault();
    const id = document.getElementById("idProyecto").value;
    const nombre = document.getElementById("nombreProyecto").value;
    const inicio = document.getElementById("fechaInicioProyecto").value;
    const fin = document.getElementById("fechaFinProyecto").value;

    const url = id ? `${API_BASE}/proyectos/${id}` : `${API_BASE}/proyectos`;
    const method = id ? "PUT" : "POST";

    await fetch(url, {
        method,
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({nombre, fechaInicio: inicio, fechaFinalEstimada: fin})
    });

    document.getElementById("form-proyecto").reset();
    document.getElementById("idProyecto").value = "";
    cargarProyectos();
});

// Rellenar formulario para editar
function editarProyecto(id, nombre, inicio, fin) {
    document.getElementById("idProyecto").value = id;
    document.getElementById("nombreProyecto").value = nombre;
    document.getElementById("fechaInicioProyecto").value = inicio;
    document.getElementById("fechaFinProyecto").value = fin;
}

// Eliminar proyecto
async function eliminarProyecto(id) {
    await fetch(`${API_BASE}/proyectos/${id}`, {method: "DELETE"});
    cargarProyectos();
}

// ------------------- ETAPAS -------------------
async function cargarEtapas(proyectoId) {
    const res = await fetch(`${API_BASE}/proyectos/${proyectoId}/etapas`);
    const data = await res.json();
    const tbody = document.querySelector(`#tabla-etapas-${proyectoId} tbody`);
    tbody.innerHTML = "";

    if (!data._embedded) return;

    data._embedded.etapas.forEach(async etapa => {
        const presupuestoRes = await fetch(`${API_BASE}/etapas/${etapa.idEtapa}/presupuesto`);
        const presupuesto = await presupuestoRes.json();

        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${etapa.fase}</td>
            <td>${etapa.fechaInicio}</td>
            <td>${etapa.fechaFin}</td>
            <td>${presupuesto?.montoTotalEstimado ?? "-"}</td>
            <td>
                <button class="btn btn-warning btn-sm me-2" onclick="editarEtapa(${etapa.idEtapa}, '${etapa.fase}', '${etapa.fechaInicio}', '${etapa.fechaFin}', ${presupuesto?.montoTotalEstimado ?? 0})">Editar</button>
                <button class="btn btn-danger btn-sm" onclick="eliminarEtapa(${etapa.idEtapa})">Eliminar</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

// Agregar etapa y presupuesto
async function agregarEtapa(event, proyectoId) {
    event.preventDefault();
    const form = event.target;
    const fase = form[0].value;
    const inicio = form[1].value;
    const fin = form[2].value;
    const monto = form[3].value;

    const resEtapa = await fetch(`${API_BASE}/etapas`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({fase, fechaInicio: inicio, fechaFin: fin, proyecto: {idProyecto: proyectoId}})
    });
    const etapaCreada = await resEtapa.json();

    await fetch(`${API_BASE}/presupuestos`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({montoTotalEstimado: monto, montoUtilizado: 0, etapa: {idEtapa: etapaCreada.idEtapa}})
    });

    cargarProyectos();
}

// Editar etapa
async function editarEtapa(id, fase, inicio, fin, monto) {
    const nuevoFase = prompt("Fase", fase);
    const nuevoInicio = prompt("Fecha Inicio", inicio);
    const nuevoFin = prompt("Fecha Fin", fin);
    const nuevoMonto = prompt("Monto Total", monto);

    await fetch(`${API_BASE}/etapas/${id}`, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({fase: nuevoFase, fechaInicio: nuevoInicio, fechaFin: nuevoFin})
    });

    // Actualizar presupuesto
    const presupuestoRes = await fetch(`${API_BASE}/etapas/${id}/presupuesto`);
    const presupuesto = await presupuestoRes.json();
    if (presupuesto?.idPresupuesto) {
        await fetch(`${API_BASE}/presupuestos/${presupuesto.idPresupuesto}`, {
            method: "PUT",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({montoTotalEstimado: nuevoMonto, montoUtilizado: presupuesto.montoUtilizado, etapa: {idEtapa: id}})
        });
    }

    cargarProyectos();
}

// Eliminar etapa
async function eliminarEtapa(id) {
    await fetch(`${API_BASE}/etapas/${id}`, {method: "DELETE"});
    cargarProyectos();
}

// ------------------- INICIO -------------------
cargarProyectos();
