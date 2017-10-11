const Helpers = (function() {

  const registerHelpers = function() {


    Handlebars.registerHelper('render-item', function(item, bins, options) {

      console.log(item);
      console.log(bins);

      const match = /([^=])*=q(\d)/.exec(item);

      if(match === null) {
        return new Handlebar.SafeString(item);
      }

      const attributeName = match[1];
      const binIndex = match[2];

      if(bins.hasOwnProperty(attributeName)) {
        const binLow = bins[attributeName][binIndex];
        const binHigh = bins[attributeName][binIndex + 1];

        return new Handlebars.SafeString(
          attributeName + " in " + binLow.toFixed(2) + " - " + binHigh.toFixed(2)
        );
      } else {
        return new Handlebar.SafeString(item);
      }
    });

    Handlebars.registerHelper('rate-bin-selector', function(rateBins, options) {
      var out  = '<ul class="nav nav-tabs">';

      for(var i=0; i < rateBins.length - 1; i++) {
        const rateLowInMinutes = (rateBins[i] * 60.0).toFixed(2);
        const rateHighInMinutes = (rateBins[i + 1] * 60.0).toFixed(2);

        out += '<li><a href="#"> Rate in ' + rateLowInMinutes + " - " + rateHighInMinutes + " percents/min" + '</a></li>';
      }

      return out + '</ul>';
    });

  };

  return {
    registerHelpers: registerHelpers
  };

})();

