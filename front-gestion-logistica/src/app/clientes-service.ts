import { AppService } from './app.service';
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Clientes } from "./clientes.model";



@Injectable()
export class ClientesService {
  constructor(private httpClient: HttpClient, private app: AppService){}
  clientes: Clientes[]=[];
  urlBase="http://localhost:4200/api/clientes";

  loadCustomers() {
    let  credentials= this.app.getCredentials();
    let headers_object = new HttpHeaders();
     headers_object =   headers_object.append('Authorization', 'Basic '.concat(btoa(credentials.username.concat(':').concat(credentials.password))));
     const httpOptions = {
      headers: headers_object
};
    return this.httpClient.get(this.urlBase, httpOptions);
  }

  save(clientes: Clientes) {
    let  credentials= this.app.getCredentials();
    let headers_object = new HttpHeaders();
     headers_object =   headers_object.append('Authorization', 'Basic '.concat(btoa(credentials.username.concat(':').concat(credentials.password))));
     const httpOptions = {
      headers: headers_object
};
    return this.httpClient.post(this.urlBase, clientes, httpOptions);
  }

  setClientes(clientes: Clientes[]){
    this.clientes = clientes;
  }

}
