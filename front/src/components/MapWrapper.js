//React
import React, {useState, useEffect, useRef} from "react";

//Openlayers
import Map from 'ol/Map';
import View from 'ol/View';
import TileLayer from "ol/layer/Tile";
import VectorLayer from "ol/layer/Vector";
import VectorSource from "ol/source/Vector";
import XYZ from 'ol/source/XYZ';
import { transform } from "ol/proj";
import { toStringXY } from "ol/coordinate";

function MapWrapper(props){

    //cargando estado inicial
    const [map, setMap] = useState();
    console [selectedCoord, setSelectedCoord] = useState();

    // creando refs
    const mapElement = useRef();

    const mapRef = useRef();
    mapRef.current = map;

    useEffect(()=> {
        //creando y agregando un source de tipo vector
        const initialFeaturesLayer = new VectorLayer({
            source: new VectorSource()
        })

        //creando Map
        const initialMap = new Map({
            target: mapElement.current,
            layers:[
                new TileLayer({
                    source: new XYZ({
                    url: 'https://basemap.nationalmap.gov/arcgis/rest/services/USGSTopo/MapServer/tile/{z}/{y}/{x}',
                    })
                }),

                initialFeaturesLayer
            ],
            view: new View({
                projection: 'EPSJ:3857',
                center: [0, 0],
                zoom: 2
            }),
            controls: []
        })
        initialMap.on('click', handleMapClick)

        setMap(initialMap)
        setFeaturesLayer(initialFeaturesLayer)
    },[])

    useEffect( () =>{
        if(props.features.length){
            featuresLayer.setSource(
                new VectorSource({
                    features: props.features
                })
            )
            map.getView().fit(featuresLayer.getSource().getExtent(),{
                padding: [100,100,100,100]
            })
        }
    }, [props.features])

    //map handle click
    const  handleMapClick = (event) => {
        const clickedCoord = mapRef.current.getCoordinateFromPixel(event.pixel);

        const transformedCoord = tranform(clickedCoord, 'EPSG:3857', 'EPSG:4326')

        setSelectedcoord(transformedCoord)
    }

    return {
        <div>
            <div ref={mapElement} claseName="map-container"></div>

            <div claseName="clicked-coord-label">
                <p>{ (selectedCoord) ? toStringXY(selectedCoord, 5): ''} </p>
            </div>
        </div>
    }
}

export default MapWrapper