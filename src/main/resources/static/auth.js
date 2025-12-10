// ==============================
// Helpers de mensagens
// ==============================
function mensagemErro(msg) {
    document.getElementById("msg").innerHTML =
        `<div class="alert alert-danger">${msg}</div>`;
}
function mensagemSucesso(msg) {
    document.getElementById("msg").innerHTML =
        `<div class="alert alert-success">${msg}</div>`;
}

// ==============================
// Controle de auth
// ==============================
function salvarToken(token) {
    localStorage.setItem("jwt", token);
}
function obterToken() {
    return localStorage.getItem("jwt");
}
function logout() {
    localStorage.removeItem("jwt");
    window.location.href = "/login";
}
function verificarLoginOuRedirecionar() {
    if (!obterToken()) {
        window.location.href = "/login";
    }
}
function carregarUsuarioNaNavbar() {
    const email = localStorage.getItem("emailUsuario");
    if (email) {
        const div = document.getElementById("userInfo");
        if (div) div.innerHTML = `👋 Olá, ${email}`;
    }
}

// ==============================
// Fetch autenticado
// ==============================
async function authFetch(url, options = {}) {
    const token = obterToken();

    if (!options.headers) options.headers = {};
    options.headers["Authorization"] = "Bearer " + token;

    return await fetch(url, options);
}

// ==============================
// Login
// ==============================
async function fazerLogin(email, senha) {
    const resp = await fetch("/login", {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({ email, senha })
    });

    if (!resp.ok) return null;

    const json = await resp.json();

    salvarToken(json.token);
    localStorage.setItem("emailUsuario", email);

    return json;
}
