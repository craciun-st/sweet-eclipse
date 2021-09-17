export function getFirstSentenceUpTo(s: string, upperBound: number): string {
    let sentences = s.split(/[.!?]/);
    let first = sentences[0];
    if (first.length < upperBound) {
        return first;
    } else {
        return first.substring(0, upperBound-3)+'...'
    }

}