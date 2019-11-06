export class User {

    id:number;
    login:string;
    password:string;
    name:string;
    profile:UserProfile;
    

}

export enum UserProfile {

    USER = 'USER',
    ADMIN = 'ADMIN'

}