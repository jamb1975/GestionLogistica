import { ClientesService } from './../clientes-service';
import { Clientes } from './../clientes.model';
import { Component, OnInit, ViewChild   } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';


export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
}


@Component({
  templateUrl: './clientes.component.html',
  styles: [
  ]
})
export class ClientesComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nombres', 'no_identificacion', 'no_telefono'];

  clientes: Clientes[] =[];
  constructor(private clientesService: ClientesService, private http: HttpClient,
              private router: Router
            ) { }

  ngOnInit(): void {
    this.clientesService.loadCustomers()
    .subscribe({

      next: (listaClientesRemote)=>{
        let clientesRemote =  listaClientesRemote  as Clientes[];
        this.clientes = clientesRemote
        this.clientesService.setClientes(this.clientes);
        console.log("Clientes Obtenidas subscriber " + this.clientes);
       
      }
    }

    );
  }

  irAgregar(){
    console.log("Vamos a agregar");
    this.router.navigate(['/agregarcliente'])
  }

}
