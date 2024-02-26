import { Line, Pie } from 'react-chartjs-2';
import 'chart.js/auto';
import './Profile.css'
import Card from './Card';
const Profile = () => {
  const data = {
    labels: ['Assigned', 'Pending', 'Process', 'Done', 'Bug', 'Priority'],
    datasets: [
      {
        label: 'My First dataset',
        // fill: false,
        // lineTension: 0.1,
        // backgroundColor: 'rgba(75,192,192,0.4)',
        // borderColor: 'rgba(75,192,192,1)',
        // borderCapStyle: 'butt',
        // borderDash: [],
        // borderDashOffset: 0.0,
        // borderJoinStyle: 'miter',
        // pointBorderColor: 'rgba(75,192,192,1)',
        // pointBackgroundColor: '#fff',
        // pointBorderWidth: 1,
        // pointHoverRadius: 5,
        // pointHoverBackgroundColor: 'rgba(75,192,192,1)',
        // pointHoverBorderColor: 'rgba(220,220,220,1)',
        // pointHoverBorderWidth: 2,
        // pointRadius: 1,
        // pointHitRadius: 10,
        data: [6, 5, 8, 8, 5, 5, 4]
      }
    ]
  };

  const userDetails = {
    name: 'John Doe',
    email: 'john@example.com',
    project: 'Project X',
    role: 'Developer',
    tickets: ['Ticket #1', 'Ticket #2', 'Ticket #3']
  };

  return (
    <>
      <h1>This is proifle page</h1>
      <div className="Details">
        <div className="chartProfile">
          <Card {...userDetails} />
        </div>
        <div className='chartProfile'>
          <Pie data={data} />
        </div>
      </div>

    </>
  )
}

export default Profile
