import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-emailus',
  templateUrl: './emailus.component.html',
  styleUrls: ['./emailus.component.css']
})
export class EmailusComponent implements OnInit {

  public data:any=[]
 
  ngOnInit(): void {
      
  }
  constructor(private http: HttpClient){
  }
 
  save(name, email, mobile, subject, message): void {
    this.data['name']= name;
                this.data['email']= email;
                this.data['mobile']= mobile;
                this.data['subject']= subject;
                this.data['message']= message;
    console.log(this.data);
                //add request to send email or into mysql
                this.http.put('http://localhost/api/v1/update/', this.data).subscribe(
        res => {
          console.log(res);
      },
      // (err: HttpErrorResponse) => {
      //   if (err.error instanceof Error) {
      //     console.log("Client-side error occured.");
      //   } else {
      //     console.log("Server-side error occurred.");
      //   }
      // }
      );
   }
}
