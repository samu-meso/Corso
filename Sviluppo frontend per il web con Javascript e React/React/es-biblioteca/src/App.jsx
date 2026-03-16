import 'bootstrap/dist/css/bootstrap.min.css';
import book1Img from './assets/libro1.jpg';
import book1Isbn from './assets/libro1_isbn.png';
import book2Img from './assets/libro2.jpg';
import book2Isbn from './assets/libro2_isbn.png';
import book3Img from './assets/libro3.jpg';
import book3Isbn from './assets/libro3_isbn.jpg';
import BookList from './components/BookList';


const book_list = [
  {
    title: "La teoria di lasciare andare",
    desc: "Lascia andare: e se la chiave per la felicita', il successo e l'amore fosse semplice come queste due parole?",
    author: "Mel Robbins",
    longDesc: "Lascia andare esplora come due semplici parole possano diventare la chiave per liberarsi dal peso del passato. Tra felicità, successo e amore, guida a cambiare prospettiva e a ritrovare spazio mentale ed emotivo. Un invito pratico a mollare il controllo e vivere con più leggerezza, presenza e fiducia.",
    img: book1Img,
    isbn: book1Isbn,
  },
  {
    title: "Succede sempre qualcosa di meraviglioso",
    desc: "Un viaggio in Vietnam dove Davide, dopo aver perso ogni certezza, incontra Guilly e scopre un modo nuovo e luminoso di guardare alla vita.",
    longDesc: "Succede sempre qualcosa di meraviglioso è il racconto di un viaggio che ha come protagonista Davide, un ragazzo che vede tutte le sue certezze crollare una dopo l'altra, fino a perdere il desiderio di vivere. E Guilly, un personaggio fuori dal tempo che Davide, per caso o per destino, incontra in Vietnam e da cui apprende un modo alternativo e pieno di luce di prendere la vita.",
    author: "Gianluca Gotto",
    img: book2Img,
    isbn: book2Isbn,
  },
    {
    title: "Sporco ricco",
    desc: "Da questo libro la nuova serie Netflix sul caso Jefrey Epstein. “Il più potente e disgustoso dei casi venuti a galla con il #MeToo.”",
    longDesc: "«Si parlerà ancora a lungo del triste caso di Jeffrey Epstein, dei suoi turpi delitti, delle sue pacchiane dimore, dei suoi amici, e ovviamente della sua morte, così annunciata da apparire sospetta anche agli allergici del complottismo». - Emanuele Trevi, “7 – Corriere della Sera” “Il presidente ha effettuato quattro viaggi sull’aeroplano di Epstein... Tutti di lavoro.” Portavoce di Bill Clinton “Sono stato suo ospite e mai testimone del comportamento che ha portato al suo arresto e alla sua condanna.” Principe Andrea d’Inghilterra Una storia drammaticamente vera raccontata dal re mondiale del thriller. Uno scandalo che ha travolto un gigante della finanza e l’establishment internazionale, scoperchiando un intrico di sesso e potere, ricatto e violenza. Era il 2016 quando James Patterson portò alla ribalta l’affaire Epstein, ricostruendo per primo la vicenda che aveva investito il magnate e la sua vasta cerchia di amici. A quattro anni di distanza, lo scrittore torna sul caso a seguito di sconcertanti sviluppi, ultimo dei quali, la misteriosa morte del protagonista. I soldi possono fare molto, persino imbavagliare le vittime e comprare l’immunità del carnefice. All’uscita della prima edizione di questo libro in America, il miliardario ci è quasi riuscito, ottenendo una condanna irrisoria che gli ha lasciato ampia libertà di proseguire la sua esistenza dorata e perpetrare abusi ai danni di ragazzine adescate per pochi dollari. Ma quando nuove accuse lo inchiodano e lo confinano nell’isolamento di una cella, e altre vittime prendono coraggio, il suo mondo trema, l’entourage si dissocia: se lui parla, sarà la fine. La mattina del 10 agosto 2019 i secondini lo trovano con il collo stretto in un lenzuolo. Le guardie che dovevano sorvegliarlo pare si siano addormentate. È suicidio, dirà l’archiviazione.",
    author: "James Patterson, John Connolly, Tim Malloy",
    img: book3Img,
    isbn: book3Isbn,
  }
]




function App() {
  console.log("APP()")
  return (
    <>
      <h1>Biblioteca</h1>
      <BookList bookList={book_list} />
    </>
  )
}

export default App;
