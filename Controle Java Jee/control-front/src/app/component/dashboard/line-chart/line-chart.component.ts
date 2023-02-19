import { Component, OnInit } from '@angular/core';
import {SmoothieChart, TimeSeries} from "smoothie";

@Component({
  selector: 'app-line-chart',
  templateUrl: './line-chart.component.html',
  styleUrls: ['./line-chart.component.css']
})

export class LineChartComponent implements OnInit{
  index=1;
  customers=["1","2","3"];
  colors=[{stroke :'rgba(0,255,0,1)',fill:'rgba(0,255,0,0.2)'},{stroke :'rgba(255,0,0,1)',fill:'rgba(255,0,0,0.2)'}, {stroke :'rgba(0,0,255,1)',fill:'rgba(0,0,255,0.2)'}];
  courbe:{ [key: string]: TimeSeries } = {};
  smoothieChart=new SmoothieChart({
    tooltip:true,
    grid: { strokeStyle: 'rgba(125, 0, 0, 0.1)', fillStyle: 'rgba(60, 0, 0, 0.1)', lineWidth: 1, millisPerLine: 250, verticalSections: 6 },
    labels: { fillStyle: '#ffffff' },
    timestampFormatter: SmoothieChart.timeFormatter
  });
  ngOnInit(): void {

    this.smoothieChart.streamTo(document.getElementById("canvas") as HTMLCanvasElement,500);
    this.customers.forEach((v)=>{
      this.courbe[v]=new TimeSeries();
      var col=this.randomColor();
      this.smoothieChart.addTimeSeries(this.courbe[v],{strokeStyle:col.stroke,fillStyle:col.fill,lineWidth:4,tooltipLabel:v.substring(0,9)});
      console.log(v.substring(0,9))
    });

    var stockEventSource=new EventSource("http://localhost:8089/analytics");
    stockEventSource.addEventListener("message",(event)=>{

      this.customers.forEach((v)=>{
        var val=JSON.parse(event.data)[v];
        this.courbe[v].append(new Date().getTime(),val);
      });

    });
  }

  randomColor()
  {
    this.index=this.index+1;
    if(this.index>=this.colors.length)
      this.index=0;
    return this.colors[this.index];
  }

}
