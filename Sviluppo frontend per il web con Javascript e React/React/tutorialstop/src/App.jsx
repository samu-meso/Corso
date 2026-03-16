import 'bootstrap/dist/css/bootstrap.min.css';
import reactImg from './assets/react.png'
import nextjsImg from './assets/nextjs.png'
import angularImg from './assets/angular.png'
import svelteImg from './assets/svelte.png'
import Tutorials from './components/Tutorials';
import FormTutorial from './components/FormTutorials';
import { useState } from 'react';
import Comments from './components/Comments';
import Interviews from './components/Interviews';
import Posts from './components/Posts';


const tutorials = [
  {
    topic: "React",
    language: "JS/TypeScript",
    title: "First app in React",
    excerpt: "React.js Lorem Ipsum è un testo segnaposto.",
    allText: "React.js Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.",
    readingTime: 15,
    pic: reactImg,
    isDifficult: false,
    website: {
      title: "React site",
      link: "https://react.dev/",
    }
  },
  {
    topic: "Next.js",
    language: "TypeScript",
    title: "A React-based framework!",
    excerpt: "Next.js Lorem Ipsum è un testo segnaposto.",
    allText: "Next.js Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.",
    readingTime: 12,
    pic: nextjsImg,
    isDifficult: true,
    website: {
      title: "Next.js site",
      link: "https://nextjs.org/",
    }
  },
  {
    topic: "Angular",
    language: "TypeScript",
    title: "Angular 18 is out!",
    excerpt: "Angular Lorem Ipsum è un testo segnaposto.",
    allText: "Angular Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.",
    readingTime: 10,
    pic: angularImg,
    isDifficult: false,
    website: {
      title: "Angular site",
      link: "https://angular.dev/",
    }
  },
  {
    topic: "Svelte",
    language: "JS/TypeScript",
    title: "Cybernetically enhanced web apps",
    excerpt: "Svelte Lorem Ipsum è un testo segnaposto.",
    allText: "Svelte Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.",
    readingTime: 14,
    pic: svelteImg,
    isDifficult: false,
    website: {
      title: "Svelte site",
      link: "https://svelte.dev/",
    }
  },
  {
    topic: "React",
    language: "JS/TypeScript",
    title: "First app in React",
    excerpt: "React.js Lorem Ipsum è un testo segnaposto.",
    allText: "React.js Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.",
    readingTime: 15,
    pic: reactImg,
    isDifficult: false,
    website: {
      title: "React site",
      link: "https://react.dev/",
    }
  },
  {
    topic: "Angular",
    language: "TypeScript",
    title: "Angular 18 is out!",
    excerpt: "Angular Lorem Ipsum è un testo segnaposto.",
    allText: "Angular Lorem Ipsum è un testo segnaposto utilizzato nel settore della tipografia e della stampa. Lorem Ipsum è considerato il testo segnaposto standard sin dal sedicesimo secolo, quando un anonimo tipografo prese una cassetta di caratteri e li assemblò per preparare un testo campione.",
    readingTime: 10,
    pic: angularImg,
    isDifficult: false,
    website: {
      title: "Angular site",
      link: "https://angular.dev/",
    }
  }
];


function App() {
  console.log("APP()");


  const [comments, setComments] = useState([]);

  function addComment(comment) {
    // comments.push(comment); // NO
    setComments((cs) => [...cs, comment])
  }

  return (
    // Fragments
    <>
      <h1>Main Page!</h1>
      <Posts />
      <FormTutorial tutorialsP={tutorials} onAddComment={addComment} />
      <Comments commentsP={comments} />

      <Tutorials tutorialsP={tutorials} />
      <Interviews />
    </>
  )
}

export default App;