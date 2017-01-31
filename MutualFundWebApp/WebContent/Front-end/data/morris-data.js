$(function() {
    Morris.Area({
        element: 'morris-area-chart',
        data: [{
            period: '2010 Q1',
            iphone: 15,
        }, {
            period: '2010 Q2',
            iphone: 27,
        }, {
            period: '2010 Q3',
            iphone: 49,
        }, {
            period: '2010 Q4',
            iphone: 37,
        }, {
            period: '2011 Q1',
            iphone: 68,
        }, {
            period: '2011 Q2',
            iphone: 56,
        }, {
            period: '2011 Q3',
            iphone: 48,
        }, {
            period: '2011 Q4',
            iphone: 150,
        }, {
            period: '2012 Q1',
            iphone: 107,
        }, {
            period: '2012 Q2',
            iphone: 84,
        }],
        xkey: 'period',
        ykeys: ['iphone'],
        labels: ['iPhone'],
        pointSize: 2,
        hideHover: 'auto',
        resize: true
    });
});
