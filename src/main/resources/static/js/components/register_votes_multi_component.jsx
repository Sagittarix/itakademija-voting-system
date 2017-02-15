var RegisterVotesMultiComponent = React.createClass( {
    render: function() {
        var self = this;
        var votesList = [];
        var votesEntered = [];

        this.props.elections.map( function( electVotes, index ) {
            votesList.push( electVotes.party.id );
            votesEntered.push( electVotes );
        });

        console.log( 'bla' );
        var partyCount = [];


        var partyList = this.props.parties.map( function( party, index ) {
            partyCount.push( party );

            if ( votesList.includes( party.id ) ) {

                for ( var i = 0; i < votesEntered.length; i++ ) {
                    if ( votesEntered[i].party.id == party.id ) {
                        return (
                            <div key={index}>
                                <label>{party.title} ({party.party_abbreviation} )</label>
                                <div>Įvestas rezultatas: {votesEntered[i].votes}</div><br />
                            </div> )
                    }
                }


            } else {

                return (
                    <div key={index}>
                        <label>{party.title} ({party.party_abbreviation} )</label>
                        <VoteFormMultiContainer partyId={party.id} /><br />
                    </div>
                );
            }

        });

        console.log( partyCount.length );
        var disabled = true;
        if ( partyCount.length != 0 && votesEntered.length == partyCount.length && votesEntered[0].published_date == null ) {
            disabled = false;
        }

        return (
            <form>
                <h3>Daugiamandatės</h3>
                <LoggedInRepresentativeInfoContainer />
                {partyList}
                <input id="submitMulti" type="checkbox" disabled /> Patvirtinu, kad įvesti duomenys teisingi.<br />
                <button id="publishMulti" className="btn btn-success" onClick={self.props.onPublishVotes} disabled={disabled}>Publikuoti rezultatus</button>
            </form>
        )
    }
});

RegisterVotesMultiComponent.propTypes = {
    parties: React.PropTypes.array.isRequired,
    elections: React.PropTypes.array.isRequired
};

window.RegisterVotesMultiComponent = RegisterVotesMultiComponent;