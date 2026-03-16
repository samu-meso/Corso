const textInput = document.getElementById('textInput');
const liveText = document.getElementById('liveText');
const keyBox = document.getElementById('keyBox');
const counterBtn = document.getElementById('counterBtn');
const colorPicker = document.getElementById('colorPicker');
const card = document.getElementById('mainCard');
const colorRect = document.getElementById('colorRect');
let count = 0;

// Colori per il rettangolo
const rectColors = [
    "#ef5350", "#ab47bc", "#42a5f5", "#26a69a",
    "#d4e157", "#ff7043", "#8d6e63", "#78909c",
    "#ec407a", "#66bb6a"
];
let colorIndex = 0;

// 1. MOUSEENTER: Cambia colore al titolo
title.addEventListener('mouseenter', () => title.style.color = '#ff5722');
title.addEventListener('mouseleave', () => title.style.color = '#333');

myFunc = (text) => {
    let testo = text;
    let invertito = "";
    // console.log(testo.length - 1)
    for (let i = testo.length - 1; i >= 0; i--) {
        console.log(testo[i])
        // parola c i a o
        //        0 1 2 3 
        // legge 3 quindi o
        // legge 2 quindi a
        // legge 1 quindi i
        // legge 0 quindi c
        
        invertito += testo[i];
    }
    return invertito
}
// 2. INPUT: Legge cosa scrivi in tempo reale
textInput.addEventListener('input', (e) => {
    textInverted = myFunc(e.target.value);
    liveText.innerText = "Stai scrivendo: " + textInverted;
    textInput.value = e.target.value;
});

// 3. KEYDOWN: Rileva il tasto premuto
textInput.addEventListener('keydown', (e) => {
    keyBox.innerText = "Ultimo tasto: " + e.key;
    keyBox.style.background = "#e1f5fe";
});

// 4. CLICK: Contatore semplice
counterBtn.addEventListener('click', () => {
    count++;
    counterBtn.innerText = `Cliccami: ${count} (click)`;
});

// 5. CHANGE: Cambia colore allo sfondo del body
colorPicker.addEventListener('change', (e) => {
    document.body.style.backgroundColor = e.target.value;
});

// 6. CLICK sul rettangolo: cambia colore ad ogni click
colorRect.addEventListener('click', () => {
    colorIndex = (colorIndex + 1) % rectColors.length;
    const newColor = rectColors[colorIndex];
    colorRect.style.background = newColor;
    colorRect.style.borderColor = "transparent";
    colorRect.style.color = "#fff";
    colorRect.innerText = `Colore: ${newColor}`;
});

// DBLCLICK: Reset
card.addEventListener('dblclick', () => {
    count = 0;
    counterBtn.innerText = "Cliccami: 0 (click)";
    textInput.value = "";
    liveText.innerText = "Feedback in tempo reale...";
    keyBox.innerText = "Premi un tasto...";
    document.body.style.backgroundColor = "#ffffff";
    colorPicker.value = "#ffffff";
    // Reset rettangolo
    colorIndex = 0;
    colorRect.style.background = "#cccccc";
    colorRect.style.borderColor = "#aaa";
    colorRect.style.color = "#333";
    colorRect.innerText = "Clicca per cambiare colore!";
    alert("Esercizio Resettato!");
});