var DeleteVotesComponent = React.createClass({
    render: function() {
        
        var constituencyList = this.props.constituencies.map( function( constituency, index ) {
          return (
              <option key={index}>{constituency.title}</option>
          );
        });
        var districtList = this.props.districts.map( function( district, index ) {
            return (
                <option key={index} value={district.id}>{district.title}</option>
            );
          });
        
      return (
              
              <form>
              <label>Apygarda (neveikia)</label><br />
                  <select className="form-control" disabled>
                      <option value="base">Pasirinkite apygardą</option>
                      {constituencyList}
                  </select>
              <label>Apylinkė</label><br />
                  <select className="form-control"  value={this.props.districtVal} onChange={this.props.onDistrictChange}>
                      <option value="base">Pasirinkite apylinkę</option>
                      {districtList}
                  </select>
              <label>Rinkimų tipas (neveikia)</label><br />
                  <select className="form-control" value={this.props.electionType} onChange={this.props.onTypeChange}>
                      <option value="base">Pasirinkite rinkimų tipą</option>
                      <option value="single">Vienmandate</option>
                      <option value="multi">Daugiamandate</option>
                  </select>
                  <button className="btn btn-danger" onClick={this.props.onDeleteVotes}>Anuliuoti rezultatus</button>
              </form>
              
              )
    }
});

window.DeleteVotesComponent = DeleteVotesComponent;