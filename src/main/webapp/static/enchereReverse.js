/**
 * 
 */

//Recuperation du container
const container = document.getElementById('enchereContainer');

//Tableau des elements enfants
const items = Array.from(container.children);

//Inverse l'ordre des éléments
items.reverse().forEach(item => container.appendChild(item));