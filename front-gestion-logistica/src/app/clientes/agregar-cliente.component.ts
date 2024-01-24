import { Component, OnInit, ElementRef,
  Inject,
  Input,
  OnDestroy,
  Optional,
  Self,
  ViewChild } from '@angular/core';
import { ClientesService } from './../clientes-service';
import { Clientes } from './../clientes.model';
import { Router } from '@angular/router';
import {FormControl, Validators, FormGroup,  FormBuilder, AbstractControl,  ControlValueAccessor, NgControl } from '@angular/forms';
import {
  MAT_FORM_FIELD,
  MatFormField,
  MatFormFieldControl

} from '@angular/material/form-field';
@Component({
  selector: 'app-agregar-cliente',
  templateUrl: './agregar-cliente.component.html',
  styleUrls: ['./agregar-cliente.component.css']


})
export class AgregarClienteComponent implements OnInit {
  form: FormGroup = new FormGroup({
    no_telefono: new FormControl(new MyTel('', '', '')),
  });
  email = new FormControl('', [Validators.required, Validators.email]);
  cliente = new Clientes(null, "", "","","", "");
  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }

  hide = true;
  step = 0;

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }
  constructor(private clientesService: ClientesService, private router: Router  ) { }

  ngOnInit(): void {
  }

  save(){
   this.clientesService.save(this.cliente)
   .subscribe({

    next: (listaClientesRemote)=>{
      let clientesRemote =  listaClientesRemote  as Clientes;
      this.cliente = clientesRemote
      this.router.navigateByUrl('/clientes');

    }
  }

  );;

  }

}
export class MyTel {
  constructor(public area: string, public exchange: string, public subscriber: string) {}
}

