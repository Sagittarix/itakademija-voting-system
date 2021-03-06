var App = React.createClass( {
    render: function() {
        return (
            <div style={{ paddingTop: '10px' }}>
                <img style={{ paddingBottom: '10px' }} src="images/vrk-logo-lt.png" alt="vrk logo" />
                <NavigationPubContainer />
                {this.props.children}
                <div className="container footer navbar-fixed-bottom">
                &copy; KALM, 2017 <br/>
                <a id="apiresults" href="/#/apiresults">API SERVISAS</a>
                
                </div>
            </div>
        );
    }
});

var NoMatch = React.createClass( {
    render: function() {
        return <div><h4>Atsiprašome puslapis nerastas</h4></div>;
    }
});

var Router = ReactRouter.Router;
var Route = ReactRouter.Route;
var IndexRoute = ReactRouter.IndexRoute;
var hashHistory = ReactRouter.hashHistory;

ReactDOM.render((
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={HomeComponent} />
            <Route path="/home" component={HomeComponent} />
            <Route path="/candidates" component={CandidateSearchContainer} />
            <Route path="/results" component={SingleElectionsResultsContainer} />
            <Route path="/multiresults" component={MultiElectionsResultsContainer} />
            <Route path="/multidisresult/:conId" component={MultiElectionsDistrictsResultsContainer} />
            <Route path="/multionedis/:disId" component={MultiElectionsOneDistrictResultsContainer} />

            <Route path="/disresult/:conId" component={SingleElectionsDistrictResultsContainer} />
            <Route path="/onedisresult/:disId" component={SingleElectionsOneDistrictResultsContainer} />

            <Route path="/finresults" component={PartyListContainer} />
            <Route path="/members" component={MembersOfParliamentContainer} />

            <Route path="/apiresults" component={ApiComponent} />
        
            <Route path="*" component={NoMatch} />
        </Route>
    </Router>
), document.getElementById( 'root_pub' ) );
