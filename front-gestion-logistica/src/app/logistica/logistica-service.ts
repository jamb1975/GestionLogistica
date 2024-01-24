import { AppService } from '../app.service';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Logistica } from "./logistica.model";

@Injectable()
export class LogisticaService {
  constructor(private httpClient: HttpClient, private app: AppService){}
  logisticas: Logistica[]=[];
  urlBase="http://localhost:4200/api/logistica";

  loadLogisticas() {
    let  credentials= this.app.getCredentials();
    let headers_object = new HttpHeaders();
     headers_object =   headers_object.append('Authorization', 'Basic '.concat(btoa(credentials.username.concat(':').concat(credentials.password))));
     const httpOptions = {
      headers: headers_object
};
    return this.httpClient.get(this.urlBase, httpOptions);
  }


  save(logistica: Logistica) {
   
    let  credentials= this.app.getCredentials();
    let headers_object = new HttpHeaders();
     headers_object =   headers_object.append('Authorization', 'Basic '.concat(btoa(credentials.username.concat(':').concat(credentials.password))));
     const httpOptions = {
      headers: headers_object

};
    return this.httpClient.post(this.urlBase, logistica, httpOptions);
  }

  setLogisticas(logisticas: Logistica[]){
    this.logisticas = logisticas;
  }

}
