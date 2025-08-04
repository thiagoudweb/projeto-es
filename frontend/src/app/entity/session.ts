import { Time } from "@angular/common";

export interface Session {
    mentorId?: number; 
    mentoredId?: number; 
    date: Date;
    time: Time;
    meetingTopic: string;
    status: string;
    location: string;
}