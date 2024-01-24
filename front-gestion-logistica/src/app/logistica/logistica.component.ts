import { LogisticaService } from './logistica-service';
import { Logistica} from './logistica.model';
import { ViewChild   } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-logistica',
  templateUrl: './logistica.component.html',
  styleUrls: ['./logistica.component.css']
})
export class LogisticaComponent implements OnInit {

  displayedColumns: string[] = ['id', 'fechaRegistro', 'fechaEntrega', 'precioEnvio', 'numeroFlota'];

  logisticas: Logistica[] =[];
  constructor(private logisticaService: LogisticaService, private http: HttpClient,
              private router: Router
            ) { }

  ngOnInit(): void {
    this.logisticaService.loadLogisticas()
    .subscribe({

      next: (listaLogisticaRemote)=>{
        let logisticaRemote =  listaLogisticaRemote  as Logistica[];
        this.logisticas = logisticaRemote
        this.logisticaService.setLogisticas(this.logisticas);
        console.log("Logisticas Obtenidas subscriber " + this.logisticas);

      }
    }

    );
  }

  irAgregar(){
    console.log("Vamos a agregar");
    this.router.navigate(['/agregarlogistica'])
  }

}
