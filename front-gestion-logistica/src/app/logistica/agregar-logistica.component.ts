import { Clientes } from './../clientes.model';
import { Component, OnInit } from '@angular/core';
import {ElementRef,
  Inject,
  Input,
  OnDestroy,
  Optional,
  Self,
  ViewChild } from '@angular/core';
import { LogisticaService } from './logistica-service';
import { AppService } from './../app.service';
import { Logistica } from './logistica.model';
import { Bodegas } from './../bodegas/bodegas.model';
import { PuertosMaritimos } from './../puertosMaritimos/puertos-maritimos.model';
import { TipoProductos } from './../tipoproductos/tipo-productos.model';
import { Router } from '@angular/router';
import {FormControl, Validators, FormGroup,  FormBuilder, AbstractControl,  ControlValueAccessor, NgControl } from '@angular/forms';
import {
  MAT_FORM_FIELD,
  MatFormField,
  MatFormFieldControl

} from '@angular/material/form-field';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import {AsyncPipe} from '@angular/common';
@Component({
  selector: 'app-agregar-logistica',
  templateUrl: './agregar-logistica.component.html',
  styleUrls: ['./agregar-logistica.component.css']
})
export class AgregarLogisticaComponent implements OnInit {
  logistica = new Logistica(null, 0, null,null,null, null, "", "",0,0,0,"","","","");



  hide = true;
  step = 0;

  myControl = new FormControl('');
  clientes: Clientes[] =[];
  filteredOptions: any ;
  options: string[] = [];

  myControl2 = new FormControl('');
  tipoProductos: TipoProductos[] =[];
  filteredOptions2: any ;
  options2: string[] = [];

  myControl3 = new FormControl('');
  bodegas: Bodegas[] =[];
  filteredOptions3: any ;
  options3: string[] = [];

  myControl4 = new FormControl('');
  puertosMAritimos: PuertosMaritimos[] =[];
  filteredOptions4: any ;
  options4: string[] = [];


  ngOnInit() {
    this.clientes = this.appService.getClientes();
    this.clientes.forEach((element)  => this.options.push(element.nombres.concat("||").concat(String(element.id))))
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );

    this.tipoProductos = this.appService.getTipoProductos();
    this.tipoProductos.forEach((element)  => this.options2.push(element.tipo.concat("||").concat(String(element.id))))
    this.filteredOptions2 = this.myControl2.valueChanges.pipe(
      startWith(''),
      map(value => this._filter2(value || '')),
    );

    this.bodegas = this.appService.getBodegas();
    this.bodegas.forEach((element)  => this.options3.push(element.nombre.concat("||").concat(String(element.id))))
    this.filteredOptions3 = this.myControl3.valueChanges.pipe(
      startWith(''),
      map(value => this._filter3(value || '')),
    );

    this.puertosMAritimos = this.appService.getPuertosMaritimos();
    this.puertosMAritimos.forEach((element)  => this.options4.push(element.nombre.concat("||").concat(String(element.id))))
    this.filteredOptions4 = this.myControl4.valueChanges.pipe(
      startWith(''),
      map(value2 => this._filter4(value2 || '')),
    );
  }

  private _filter(value: String): String[] {
    const filterValue = value.toLowerCase();

    return this.options.filter(option => option.toLowerCase().includes(filterValue));
  }
  private _filter2(value: String): String[] {
    const filterValue2 = value.toLowerCase();

    return this.options2.filter(option => option.toLowerCase().includes(filterValue2));
  }
  private _filter3(value: String): String[] {
    const filterValue = value.toLowerCase();

    return this.options3.filter(option => option.toLowerCase().includes(filterValue));
  }
  private _filter4(value: String): String[] {
    const filterValue = value.toLowerCase();

    return this.options4.filter(option => option.toLowerCase().includes(filterValue));
  }


  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }
  constructor(private logisticaService: LogisticaService,private appService: AppService, private router: Router  ) { }



  save(){

  this.logistica.clienteId =  this.logistica.clienteId.split("||")[1];
  this.logistica.tipoProductosId =  this.logistica.tipoProductosId.split("||")[1];
  this.logistica.puertosMaritimosId =  this.logistica.puertosMaritimosId.split("||")[1]
  console.log(this.logistica);
  this.logisticaService.save(this.logistica)
   .subscribe({

    next: (logisticaRemote)=>{
      let logistica =  logisticaRemote  as Logistica;
      this.logistica = logistica;
      this.router.navigateByUrl('/logistica');


    }
  }

  );

  }
  onBlur(): void {

    if(this.logistica.cantidadProducto > 10){
      this.logistica.valorDescuento = this.logistica.precioEnvio*0.03;
      this.logistica.valorTotal = this.logistica.precioEnvio - this.logistica.valorDescuento;
     }
  }
}

