import { Injectable } from '@angular/core';
import { CuerposInterface } from './cuerposInterface';

@Injectable({
  providedIn: 'root'
})
export class CuerposService {

  constructor() { }

  cuerposList: CuerposInterface[] = [
    {
      id: 0,
      cuerpo: 'Cuerpos generales e infantería de Marina',
      titulacion: 'Titulaciones de grado',
      requisitos_edad: 'Ingreso directo con exigencia previa de titulación de grado universitario: 27 años',
      pais: 'ESP',
      photo: `https://s3.abcstatics.com/Media/201202/28/marinabosnia--644x362.jpg`,
    },
    {
      id: 1,
      cuerpo: 'Intendencia',
      titulacion: 'Los títulos universitarios oficiales de Grado, en la rama de conocimiento de Ciencias Sociales y Jurídicas vinculados con la Economía, la Empresa y el Derecho ',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://www.tribunadesanluis.com.mx/incoming/mqmwez-ejercito.jpg/alternates/LANDSCAPE_768/ejercito.jpg`,
    },
    {
      id: 2,
      cuerpo: 'Ingenieros',
      titulacion: 'Cualquier título universitario oficial',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://s2.ppllstatics.com/lagacetadesalamanca/www/multimedia/2023/03/05/cuartel-arroquia-nuevo-coronel-luis-fernando-nunez-8_1-4427966_20230305110044--1200x800.jpg`,
    },
    {
      id: 3,
      cuerpo: 'Escala Técnica de Ingenieros',
      titulacion: 'Cualquier título universitario oficial',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://www.encastillalamancha.es/wp-content/uploads/2020/02/ser_soldado.jpg`,
    },
    {
      id: 4,
      cuerpo: 'Jurídico Militar',
      titulacion: 'Titulos de Master, Licenciado o graduado en Derecho',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://imagenes.heraldo.es/files/image_990_556/files/fp/uploads/imagenes/2021/08/03/defensa.r_d.1882-1640.jpeg`,
    },
    {
      id: 5,
      cuerpo: 'Militar de intervención',
      titulacion: 'Titulos universitarios oficiales de Grado, Licenciado o Máster',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://www.politicaexterior.com/wp-content/uploads/2021/07/intervenciones-militares.jpg`,
    },
    {
      id: 6,
      cuerpo: 'Militar de sanidad: Especialidad Medicina',
      titulacion: 'Licenciado o Graduado en Medicina',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://www.redaccionmedica.com/images/destacados/las-fuerzas-armadas-ya-tienen-su-primera-promocion-de-medicos-militares-3989_620x368.jpg`,
    },
    {
      id: 7,
      cuerpo: 'Militar de sanidad: Especialidad Psicología',
      titulacion: 'Licenciado o Graduado en Psicología',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://pir.es/wp-content/uploads/2022/03/OPOSICION-PSICOLOGO-MILITAR-IMAGEN-FREEPIK.jpg`,
    },
    {
      id: 8,
      cuerpo: 'Militar de sanidad: Especialidad Farmacia',
      titulacion: 'Licenciado o Graduado en Farmacia',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://scielo.isciii.es/img/revistas/sm/v76n2//1887-8571-sm-76-02-96-gf6.jpg`,
    },
    {
      id: 9,
      cuerpo: 'Licenciado o Graduado en Odontología',
      titulacion: 'Licenciado o Graduado en Odontología',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://gacetadental.com/wp-content/uploads/2022/07/DSCN9278-768x575.jpg`,
    },
    {
      id: 10,
      cuerpo: 'Licenciado o Graduado en Veterinaria',
      titulacion: 'Licenciado o Graduado en Veterinaria',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://www.portalveterinaria.com/upload/20220503124534veterinariomilitardefensa.jpg`,
    },
    {
      id: 11,
      cuerpo: 'Licenciado o Graduado en Enfermería',
      titulacion: 'Diplomado o Graduado en Enfermería',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://coepo.com/Colegio/wp-content/uploads/2020/05/enfermera-militar.jpg`,
    },
    {
      id: 12,
      cuerpo: 'Músicas Militares',
      titulacion: 'Titulo Superior de Música o Título de Grado en Enseñanzas Artísticas Superiores de Música',
      requisitos_edad: 'Ingreso directo para adquirir la condición de militar de carrera: Desde los 18 años, hasta sin límite de edad máxima',
      pais: 'ESP',
      photo: `https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Banda_militar_corneta.jpg/1200px-Banda_militar_corneta.jpg`,
    },
  ];

  getAllCuerpos(): CuerposInterface[] {
    return this.cuerposList;
  }

  getCuerposById(id: number): CuerposInterface | undefined {
    return this.cuerposList.find(cuerpos => cuerpos.id === id);
  }
}
