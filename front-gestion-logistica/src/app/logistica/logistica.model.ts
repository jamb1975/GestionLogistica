export class Logistica {

  constructor(public id: any,
              public cantidadProducto: number,
              public clienteId: any,
              public tipoProductosId: any,
              public bodegasId: any,
              public puertosMaritimosId: any,
              public fechaRegistro: string,
              public fechaEntrega: string,
              public precioEnvio: number,
              public valorDescuento: number,
              public valorTotal: number,
              public numeroFlota: string,
              public placaVehiculo: string,
              public numeroGuia: string,
              public tipoLogistica: string

              ){}
}
