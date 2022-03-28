import React from "react";
import { render } from "react-dom";

import ol from "openlayers";

import { layer, Map, Layers } from "react-openlayers";

var resolutions = [];
var matrixIds = [];
var proj3857 = ol.proj.get("EPSG:3857");
var maxResolution = ol.extent.getWidth(proj3857.getExtent()) / 256;

for (var i = 0; i < 18; i++) {
  matrixIds[i] = i.toString();
  resolutions[i] = maxResolution / Math.pow(2, i);
}

var tileGrid = new ol.tilegrid.WMTS({
  origin: [-20037508, 20037508],
  resolutions: resolutions,
  matrixIds: matrixIds
});

// Key restricted to localhost and wz6wl262l7.codesandbox.io
var key = "z3s6k7fy9u7m1u1daeewymau";

var ign_source = new ol.source.OSM({
  url: "https://tiles.openseamap.org/seamark/{z}/{x}/{y}.png"
});

const handleRandom= () => {
    
};

const App = () => (
    <div>
  <Map view={{ center: [0, 0], zoom: 2 }}>
    <Layers>
      <layer.Tile />
    </Layers>
  </Map>
  <div><button onClick={handleRandom}>Random</button><label>Coordenadas</label><input id="txtCords" type="text"></input></div>
  </div>
);

render(<App />, document.getElementById("root"));
