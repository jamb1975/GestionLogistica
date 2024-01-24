import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';

@Injectable()
export class AppService {

  authenticated = false;
  clientes = [];
  tiposProducto = [];
  bodegas = [];
  puertosMaritimos = [];
  credentials: any;
  constructor(private httpClient: HttpClient){}
  urlBase = 'http://localhost:4200/api/token';
  urlBaseToken = 'http://localhost:9000/oauth2/token';
  urlBaseClientes="http://localhost:4200/api/clientes";
  urlBaseTiposProducto="http://localhost:4200/api/tipoProductos";
  urlBaseBodegas="http://localhost:4200/api/bodegas";
  urlBasePuertos="http://localhost:4200/api/puertosMaritimos";
  authenticate(credentials : any, callback: any) {
     this.credentials = credentials;
     let headers_object = new HttpHeaders();
     headers_object =   headers_object.append('Authorization', 'Basic '.concat(btoa(credentials.username.concat(':').concat(credentials.password))));
     const httpOptions = {
     headers: headers_object
};

console.log("HttpOptionsheaders " + httpOptions.headers.get('Access-Control-Allow-Origin'));
console.log("HttpOptions " +  httpOptions.headers.get('Content-Type'));
console.log("HttpOptions " +  httpOptions.headers.get('Authorization'));
const body = new HttpParams().set("grant_type", "client_credentials");

  console.log("HttpParams " + body.get('grant_type'));

this.httpClient.get(this.urlBase, httpOptions)
  .subscribe((response: any) => {
    this.authenticated = response;
    return callback && callback();
  });

  this.httpClient.get(this.urlBaseClientes, httpOptions)
  .subscribe((response: any) => {
    this.clientes = response;
    return callback && callback();
  });

  this.httpClient.get(this.urlBaseTiposProducto, httpOptions)
  .subscribe((response: any) => {
    this.tiposProducto = response;
    return callback && callback();
  });

  this.httpClient.get(this.urlBaseBodegas, httpOptions)
  .subscribe((response: any) => {
    this.bodegas = response;
    return callback && callback();
  });

  this.httpClient.get(this.urlBasePuertos, httpOptions)
  .subscribe((response: any) => {
    this.puertosMaritimos = response;
    return callback && callback();
  });



  return this.authenticated;


    }

    getAuthorizationToken(){
      return ' Basic '.concat(btoa('registration-client'.concat(':').concat('secret')));
    }

    getCredentials(){
      return this.credentials;
    }
    getClientes(){
      return this.clientes;
    }
    getTipoProductos(){
      return this.tiposProducto;
    }
    getBodegas(){
      return this.bodegas;
    }

    getPuertosMaritimos(){
      return this.puertosMaritimos;
    }




}
