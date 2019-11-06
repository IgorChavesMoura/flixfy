import { Format } from "./Format";
import { Category } from "./Category";
import { User } from './User';

export class VideoContent {

    id:number;
    title:string;
    duration:number = 0;
    year:number;
    picture:string;
    type:ContentType = ContentType.MOVIE;
    format:Format;
    categories:Category[];
    episodes:Episode[] = [];
    owner:User;

    

}

export class Episode {

    id:number;
    title:string;
    duration:number;

}

export enum ContentType {

    MOVIE = 'MOVIE',
    TV_SERIES = 'TV_SERIES'

}