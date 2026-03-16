import Interview from "./Interview";

const InterviewList = [
    {
        title: "Interview 1",
        text: "JS/TypeScript1",
    },
    {
        title: "Interview 2",
        text: "JS/TypeScript2",
    },
    {
        title: "Interview 3",
        text: "JS/TypeScript3",
    }
];

export default function Interviews() {
    return (
        <Interview interviews={InterviewList} />
    )
}